import _ from "lodash"
import React, { useState } from "react"
import { Redirect } from "react-router-dom"

import Error from "./Error"

const SurrenderForm = props => {
  const [newSurrender, setNewSurrender] = useState({
    name: "",
    phoneNumber: "",
    email: "",
    petName: "",
    petAge: "",
    petType: "",
    petImageUrl: "",
    vaccinationStatus: ""
  })
  const [errors, setErrors] = useState([])
  const [redirect, setRedirect] = useState(false)

  const addNewSurrender = async () => {
    try {
      const response = await fetch("/api/v1/surrender", {
        method: "POST",
        headers: new Headers({
          "Content-Type": "application/json"
        }),
        body: JSON.stringify(newSurrender)
      })
      if (!response.ok) {
        if (response.status === 422) {
          const data = await response.json()
          return setErrors(data.errors)
        } else {
          const errorMessage = `${response.status} (${response.statusText})`
          const error = new Error(errorMessage)
          throw error
        }
      } else {
        const data = await response.json()
        if (data) {
          setRedirect(true)
        }
      }
    } catch (error) {
      console.error(`Error in fetch: ${error}`)
    }
  }

  const handleInput = event => {
    setNewSurrender({
      ...newSurrender,
      [event.currentTarget.name]: event.currentTarget.value
    })
  }
  const validateInput = () => {
    let submissionErrors = {}
    const requiredFields = [
      "name",
      "phoneNumber",
      "email",
      "petName",
      "petAge",
      "petType",
      "petImageUrl",
      "vaccinationStatus"
    ]
    requiredFields.forEach(field => {
      if (newSurrender[field].trim() === "") {
        submissionErrors = { ...submissionErrors, [field]: `is required` }
      }
    })
    setErrors(submissionErrors)
    return _.isEmpty(submissionErrors)
  }

  const handleSubmit = event => {
    event.preventDefault()
    if (validateInput()) {
      addNewSurrender()
    }
  }

  if (redirect) {
    return <Redirect to="/adoptions" />
  }

  return (
    <div>
      <h2>Surrender Your Pet:</h2>
      <form onSubmit={handleSubmit} className="adoption_app">
        <div className="grid-contrainer">
          <div className="grid-x grid-padding-x">
            <div className="cell">
              <Error errors={errors} />
            </div>

            <div className="row">
              <div className="medium-6 columns">
                <label htmlFor="name">
                  Your Name:
                  <input
                    id="name"
                    type="text"
                    name="name"
                    onChange={handleInput}
                    value={newSurrender.name}
                  />
                </label>
              </div>

              <div className="medium-6 columns">
                <label htmlFor="phoneNumber">
                  Phone:
                  <input
                    id="phoneNumber"
                    type="text"
                    name="phoneNumber"
                    onChange={handleInput}
                    value={newSurrender.phoneNumber}
                  />
                </label>
              </div>
            </div>

            <div className="row">
              <div className="medium-6 columns">
                <label htmlFor="email">
                  Email:
                  <input
                    id="email"
                    type="text"
                    name="email"
                    onChange={handleInput}
                    value={newSurrender.email}
                  />
                </label>
              </div>

              <div className="medium-6 columns">
                <label htmlFor="petName">
                  Pets Name:
                  <input
                    id="petName"
                    type="text"
                    name="petName"
                    onChange={handleInput}
                    value={newSurrender.petName}
                  />
                </label>
              </div>
            </div>

            <div className="row">
              <div className="medium-6 columns">
                <label htmlFor="petImageUrl">
                  Picture of your pet:
                  <input
                    id="petImageUrl"
                    type="text"
                    name="petImageUrl"
                    onChange={handleInput}
                    value={newSurrender.petImageUrl}
                  />
                </label>
              </div>

              <div className="medium-6 columns">
                <label htmlFor="petAge">
                  Pets Age (in months):
                  <input
                    id="petAge"
                    type="text"
                    name="petAge"
                    onChange={handleInput}
                    value={newSurrender.petAge}
                  />
                </label>
              </div>
            </div>

            <div className="row">
              <div className="medium-6 columns">
                <label htmlFor="petType">
                  Pet Type:
                  <select
                    id="petType"
                    name="petType"
                    value={newSurrender.petType}
                    onChange={handleInput}
                  >
                    <option value="">Please Select</option>
                    <option value="Dogs">Dogs</option>
                    <option value="Monkeys">Monkeys</option>
                  </select>
                </label>
              </div>

              <div className="medium-6 columns">
                <label htmlFor="vaccinationStatus">
                  Vaccinated?
                  <select
                    id="vaccinationStatus"
                    name="vaccinationStatus"
                    value={newSurrender.vaccinationStatus}
                    onChange={handleInput}
                  >
                    <option value="">Please Select</option>
                    <option value="true">Yes</option>
                    <option value="false">No</option>
                  </select>
                </label>
              </div>
            </div>

            <input className="button round" type="submit" value="Submit" />
          </div>
        </div>
      </form>
    </div>
  )
}
export default SurrenderForm
