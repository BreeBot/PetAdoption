import React, { useState, useEffect } from "react"
import TypeTile from "./TypeTile.js"

const TypeList = props => {
  const [pets, setPets] = useState([])

  const getTypes = async () => {
    try {
      const response = await fetch("/api/v1/pets")
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        const error = new Error(errorMessage)
        throw error
      }
      const responseData = await response.json()


      setPets(responseData.content)
    } catch (error) {
      console.error(`Unable to complete data fetch.`)
    }
  }

  useEffect(() => {
    getTypes()
  }, [])

  const typeObjects = pets.map(petType => {
    return <TypeTile key={petType.id} petType={petType} />
  })

  return (
    <div className = "home">
    {typeObjects}

    </div>
  )
}


export default TypeList
