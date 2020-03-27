import React from "react";
import { graphql } from "react-apollo";
import PropTypes from "prop-types";
import gql from "graphql-tag";

import Title from "./Title";
import Loading from "./Loading";
import ErrorDetails from "./ErrorDetails";

/*
  PersonDetails show details of Star Wars Characters
  Use GrapQL Query
*/
export const PersonDetailsQuery = gql`
  query PersonDetailsQuery ($personId: ID!){
    Person(id: $personId) {
      id
      name
      eyeColor
      hairColor
      skinColor
      birthYear
      vehicles {
        id
        name
      }
    }  
  }
`;

const PersonDetails = ({ data: { loading, error, Person } }) => {
  if (loading) {
    return <Loading />;
  }

  if (error) {
    return (
      <ErrorDetails text="Oops! Something went wrong while requesting person details..." />
    );
  }

  return (
    <div className="person-details">
      <Title text={`${Person.name}`} />

      <div>General Information</div>
      &nbsp;

      <div>Eye Color: {Person.eyeColor}</div>
      <div>Hair Color: {Person.hairColor}</div>
      <div>Skin Color: {Person.skinColor}</div>
      <div>Birth Year: {Person.birthYear}</div>
      &nbsp;
      &nbsp;

      <div>Vehicles</div>
      &nbsp;
      {Person.vehicles.map(vehicle => (
        <div key={vehicle.id} >
          <span>
            {vehicle.name}
          </span>
        </div>
      ))}

    </div>
  );
};

PersonDetails.propTypes = {
  id: PropTypes.string.isRequired
};

export default graphql(PersonDetailsQuery, {
  options: props => ({
    variables: { personId: props.id }
  })
})(PersonDetails);
