import React from "react"
import { Link } from "react-router-dom"

const TypeTile = props => {
  const { type, description, imgUrl } = props.petType
  return (
      <div className="cell">
        <div className="card">
          <div className="card-devider">
            <h1><Link to={`/pets/${type}`}>{type}</Link></h1>
          </div>
          <Link to={`/pets/${type}`}>
            {" "}
            <img className="images thumbnail" src={imgUrl}></img>{" "}
          </Link>
          <div className="card-section">
            {description}
          </div>
          <br/>
        </div>
      </div>
  )
}

export default TypeTile
