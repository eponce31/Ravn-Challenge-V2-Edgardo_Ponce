import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect
} from "react-router-dom";

import Header from "./Header";
import Person from "./Person";
import PersonList from "./PersonList";


/*
  Routes
*/
const App = () => (

    <Router>
      <div className="app">
        <Header />
        <div className="container py4">
          <Switch>
            <Route exact path="/" component={PersonList} />
            <Route path="/person/:id" component={Person} />
            <Route path="/PersonList" component={PersonList} />
            <Route render={() => <Redirect to="/" />} />
          </Switch>
        </div>
      </div>
    </Router>
);

export default App;

