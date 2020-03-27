import React from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";

/*
  Link to PersonDetails
*/
const PersonLink = ({ person }) => (
  <div className="person-link border-1 relative  mb2">
    
    <Link to={`/person/${person.id}`} className="h5 caps bold black">
      {person.name}
    </Link>
    

  </div>
);

PersonLink.propTypes = {
  person: PropTypes.shape({
    id: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    gender: PropTypes.string.isRequired,    
    species: PropTypes.string.isRequired,    
    homeWorld: PropTypes.string.isRequired
  })
};

export default PersonLink;
