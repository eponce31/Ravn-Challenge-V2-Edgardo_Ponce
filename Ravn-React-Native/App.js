import React from "react";
import { AppRegistry } from "react-native";
import { createStackNavigator, createAppContainer } from "react-navigation";
import PersonsScreen from "./components/PersonsScreen";
import PersonDetailScreen from "./components/PersonDetailScreen";
import { ApolloClient } from "apollo-client";
import { InMemoryCache } from "apollo-cache-inmemory";
import { HttpLink } from "apollo-link-http";
import { ApolloProvider } from "react-apollo";

const MainNavigator = createStackNavigator({
  PersonsScreen: { screen: PersonsScreen },
  PersonDetailScreen: { screen: PersonDetailScreen }
});

const cache = new InMemoryCache();
const client = new ApolloClient({
  cache,
  link: new HttpLink({
    uri: "https://api.graph.cool/simple/v1/swapi"
  })
});

const MyRootComponent = createAppContainer(MainNavigator);

const App = () => (
  <ApolloProvider client={client}>
    <MyRootComponent />
  </ApolloProvider>
);

AppRegistry.registerComponent("MyApp", () => App);

export default App;
