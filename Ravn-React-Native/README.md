# React App

Single-page app that allows to search for all human characters in Star Wars saga.
Create by Edgardo Ponce Escobedo
email: eponce24@gmail.com

    Use the Apollo GraphQL client

    Use the Star Wars GraphQL API
    https://api.graph.cool/simple/v1/swapi

    GraphQL interface
    https://www.graphqlbin.com/v2/6RQ6TM

## Maintainers

- [@eponce31](https://github.com/eponce31) (Edgardo Ponce)

## Screens

# First Screen

    Show all Star Wars characters list
    Click on the character name to show Details Screen

    https://imgur.com/FfbvpEt

# Second Screen

    Show detail information about selected Star Wars character:
    - Name
    - Eye Color
    - Hair Color
    - Skin Color
    - Date Of Birth
    - List of Vehicles

    https://imgur.com/2N87uPU

## Development

This project was created with [create-react-native-app](https://github.com/react-community/create-react-native-app#create-react-native-app).

```bash
    $ npm install -g expo-cli
    $ expo init my-app
    $ cd my-app/
    $ npm start
```

## KNOW ISSUES

Navigation don't found ...

To test both screen make next change at ./App.js:

# FirstScreen

const MainNavigator = createStackNavigator({
PersonsScreen: { screen: PersonsScreen },
PersonDetailScreen: { screen: PersonDetailScreen },
});

https://imgur.com/a/e7ZwB3Z

# SecondScreen

const MainNavigator = createStackNavigator({
PersonDetailScreen: { screen: PersonDetailScreen },
PersonsScreen: { screen: PersonsScreen },
});

https://imgur.com/a/HLg7jBU
