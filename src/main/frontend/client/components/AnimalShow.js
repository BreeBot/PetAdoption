import React, { useState, useEffect } from "react"
import AdoptionForm from "./AdoptionForm.js"
import SuccessTile from "./SuccessTile.js"

const AnimalShow = props => {
  const [animal, setAnimal] = useState([])
  const [showForm, setShowForm] = useState(false)
  const [afterSubmission, setAfterSubmission] = useState(false)

  const getAnimal = async () => {
    try {
      const animalId = props.match.params.id
      const animalType = props.match.params.type
      const response = await fetch(`/api/v1/pets/${animalType}/${animalId}`)
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        const error = new Error(errorMessage)
        throw error
      }
      const responseBody = await response.json()
      setAnimal(responseBody)
    } catch (err) {
      console.error(`Error in Fetch: ${err.message}`)
    }
  }

  useEffect(() => {
    getAnimal()
  }, [])

  let vaccinated
  if (animal.vaccinationStatus) {
    vaccinated = "Yes"
  } else {
    vaccinated = "No"
  }

  const showAdoptionForm = event => {
    event.preventDefault()
    setShowForm(true)
  }

  const handleWhatToShow = () => {
    setAfterSubmission(true)
  }

  let formDisplay = ""
  if (showForm) {
    formDisplay = <AdoptionForm id={props.match.params.id} handleWhatToShow={handleWhatToShow} />
  }

  let whatToShow
  if (afterSubmission) {
    whatToShow = <SuccessTile />
  } else {
    whatToShow = (
      <div>
        <button onClick={showAdoptionForm} className="button round">
          Adopt Me!
        </button>
        {formDisplay}
      </div>
    )
  }

  return (
    <div>
      <div className="animal-show">

        <img className="images thumbnail" src={animal.imgUrl}></img>
        <h1>{animal.name}</h1>
        <ul className="no-bullet">
          <li><strong>Age:</strong> {animal.age} months old</li>
          <li>
            <strong>{animal.name}'s story:</strong> {animal.adoptionStory}
          </li>
          <li>
            <strong>Vaccination Status:</strong> {vaccinated}
          </li>
        </ul>
      </div>
      {whatToShow}
      <br/>
      <br/>
      <br/>
    </div>
  )
}

export default AnimalShow
