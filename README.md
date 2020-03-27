# eponce31

Single-page app that allows to search for all human characters in Star Wars saga.
Create by Edgardo Ponce Escobedo
email: eponce24@gmail.com

# Platforms

    - React: Ravn_React folder
    - React native: Ravn_React folder

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

## React Development

This project was created with [create-react-app](https://facebook.github.io/create-react-app/docs/getting-started).

Steps:

1. Clone project

   - Execute next command

   ```bash
   git clone https://github.com/eponce31/Ravn-Challenge-V2-Edgardo_Ponce.git
   ```

2. Open project with Visual Studio Code

   - Wirh Visual Studio open folder ..\Ravn-Challenge-V2-Edgardo_Ponce\Ravn_React

3. Install dependencies

   - On cmd go to folder ..\Ravn-Challenge-V2-Edgardo_Ponce\Ravn_React
   - Execute next command

   ```bash
   npm install
   ```

4. Run

   - On cmd go to folder ..\Ravn-Challenge-V2-Edgardo_Ponce\Ravn_React
   - Execute next command

   ```bash
   npm start
   ```

## React Native Development

This project was created with [create-react-native-app](https://github.com/react-community/create-react-native-app#create-react-native-app).

```bash
    $ npm install -g expo-cli
    $ expo init Ravn-React-Native
    $ cd Ravn-React-Native/
    $ npm start
```

Steps:

1. Clone project

   - Execute next command

   ```bash
   git clone https://github.com/eponce31/Ravn-Challenge-V2-Edgardo_Ponce.git
   ```

2. Open project with Visual Studio Code

   - Wirh Visual Studio open folder ..\Ravn-Challenge-V2-Edgardo_Ponce\Ravn-React-Native

3. Install dependencies

   - On cmd go to folder ..\Ravn-Challenge-V2-Edgardo_Ponce\Ravn-React-Native
   - Execute next command

   ```bash
   npm install
   ```

4. Run

   - On cmd go to folder ..\Ravn-Challenge-V2-Edgardo_Ponce\Ravn-React-Native
   - Execute next command

   ```bash
   npm start
   ```



## KNOW ISSUES

Navigation don't found ...

To test both screen make next change at ./App.js:

FirstScreen
===========

const MainNavigator = createStackNavigator({
  PersonsScreen: { screen: PersonsScreen },
  PersonDetailScreen: { screen: PersonDetailScreen },
});

https://imgur.com/a/e7ZwB3Z

SecondScreen
===========

const MainNavigator = createStackNavigator({
  PersonDetailScreen: { screen: PersonDetailScreen },
  PersonsScreen: { screen: PersonsScreen },
});

https://imgur.com/a/HLg7jBU

---

## Main References

    https://www.apollographql.com/docs/react/get-started/

    https://www.howtographql.com/
