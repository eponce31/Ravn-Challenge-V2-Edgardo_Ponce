import React from "react";

import { Query } from "react-apollo";
import { gql } from "apollo-boost";
import PersonLink from "./PersonLink";

/*
  Screen to show Star Wars Characters
  Use GrapQL Query
*/
const PersonList = () => {
  return (
    <Query
      query={gql`
          {
            allPersons {
                id
                name
                species {
                    name
                }
                homeworld{
                    name
                    }
            }
          }
      `}
    >
      {({ loading, error, data }) => {
        if (loading)  return <p>Loading</p>;

       if (error) return <p>Error!</p>;

        return data.allPersons.map(person => (
          <div key={person.id} >
              <PersonLink person={person} />
          </div>
        ));
        
       
      }
    
    }
    </Query>
  );
};

export default PersonList;