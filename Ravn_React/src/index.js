import React from "react";
import ReactDOM from "react-dom";
import {ApolloProvider} from "react-apollo";
import ApolloClient from "apollo-boost";

import App from "./components/App";

import { Provider as ReduxProvider } from "react-redux";

import configureStore from "./store/configureStore";
import { populateVisitedPersons } from "./store/actions";
import sessionUtil from "./utils/session";

import "./styles.css";

const client = new ApolloClient({
  uri: "https://api.graph.cool/simple/v1/swapi"
});

const store = configureStore();
const savedPersonIds = sessionUtil.getPersonIds();

store.dispatch(populateVisitedPersons(savedPersonIds));
store.subscribe(() => {
  const { visitedPersons } = store.getState();

  sessionUtil.savePersonIds(visitedPersons);
});

ReactDOM.render(
  <ApolloProvider client={client}>
    <ReduxProvider store={store}>
      <App />
    </ReduxProvider>
  </ApolloProvider>,
  document.getElementById("root")
);
