import React from "react";
import { connect } from "react-redux";

import PersonDetails from "./PersonDetails";

export const Person = ({ match, addVisitedPerson }) => {
  const personId = match.params.id;

  return (
    <div className="person">
      <PersonDetails id={personId} />
    </div>
  );
};

export default connect(
  null
)(Person);
