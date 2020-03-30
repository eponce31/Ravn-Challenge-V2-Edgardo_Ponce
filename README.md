# eponce31

Single-page app that allows to search for all human characters in Star Wars saga.
Create by Edgardo Ponce Escobedo
email: eponce24@gmail.com

# Platforms

| Platform     | Folder                  |
| ------------ | ----------------------- |
| React        | Ravn_React              |
| React native | Ravn-React-Native       |
| Kotlin       | Kotlin_GraphQL_StarWars |

    Use the Apollo GraphQL client

    Use the Star Wars GraphQL API
    https://api.graph.cool/simple/v1/swapi

    GraphQL interface
    https://www.graphqlbin.com/v2/6RQ6TM

## Maintainers

- [@eponce31](https://github.com/eponce31) (Edgardo Ponce)

# Screens

## FirstScreen

    Show all Star Wars characters list
    Click on the character name to show Details Screen

## SecondScreen

Show detail information about selected Star Wars character: - Name - Eye Color - Hair Color - Skin Color - Date Of Birth - List of Vehicles

## React

[![react first screen](https://github.com/eponce31/Ravn-Challenge-V2-Edgardo_Ponce/blob/master/ScreenShots/react/screen_1.png)]
[![react second screen](https://github.com/eponce31/Ravn-Challenge-V2-Edgardo_Ponce/blob/master/ScreenShots/react/screen_2.png)]

## React Native

[![react-native first screen](https://github.com/eponce31/Ravn-Challenge-V2-Edgardo_Ponce/blob/master/ScreenShots/react-native/screen_1.png)]
[![react-native second screen](https://github.com/eponce31/Ravn-Challenge-V2-Edgardo_Ponce/blob/master/ScreenShots/react-native/screen_2.png)]

## Kotlin

[![react-native first screen](https://github.com/eponce31/Ravn-Challenge-V2-Edgardo_Ponce/blob/master/ScreenShots/kotlin/screen_1.png)]
[![react-native second screen](https://github.com/eponce31/Ravn-Challenge-V2-Edgardo_Ponce/blob/master/ScreenShots/kotlin/screen_2.png)]

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

# FirstScreen

const MainNavigator = createStackNavigator({
PersonsScreen: { screen: PersonsScreen },
PersonDetailScreen: { screen: PersonDetailScreen },
});

# SecondScreen

const MainNavigator = createStackNavigator({
PersonDetailScreen: { screen: PersonDetailScreen },
PersonsScreen: { screen: PersonsScreen },
});

---

## Main References

    https://www.apollographql.com/docs/react/get-started/

    https://www.howtographql.com/

# Kotlin

## Tools

Android Studio 3.6.1

## Main References

https://www.apollographql.com/docs/android/essentials/get-started/

https://www.stevetrefethen.com/blog/working-with-apollo-cli

https://blog.apollographql.com/launching-apollo-graphql-on-android-40ee0b5789bd

## Kotlin Shortcomings

I. DOWNLOADING SCHEMA FILE

    1. 	Error schema donwloaded fom endpoint
    	===================================


    	1.1. Schema:download generates incompatible schema JSON

    		gradlew generateApolloSources --stacktrace

    		schema:download generates incompatible schema JSON
    		https://github.com/apollographql/apollo-tooling/issues/555

    		09:39:22.931 [ERROR] [org.gradle.internal.buildevents.BuildExceptionReporter] Caused by: java.lang.IllegalArgumentException: Failed to locate schema root node `__schema`
    		09:39:22.931 [ERROR] [org.gradle.internal.buildevents.BuildExceptionReporter]   at com.apollographql.apollo.compiler.parser.Schema$Companion.locateSchemaRootNode(Schema.kt:172)
    		09:39:22.931 [ERROR] [org.gradle.internal.buildevents.BuildExceptionReporter]   at com.apollographql.apollo.compiler.parser.Schema$Companion.parse(Schema.kt:148)

    	1.2. Caused by: com.squareup.moshi.JsonDataException: Required property 'types_' missing at $.__schema

    2.  Downloading schema.json from schema defined by SDL
    	https://github.com/apollographql/apollo-ios/issues/660

    	I would say your question about SDL format support is probably a better one for the apollo-tooling

    3. Solution: Use Apollo-tooling

    	https://github.com/apollographql/apollo-tooling
    	https://www.apollographql.com/docs/devtools/cli/


    	mkdir apollo_cli_src

    	cd apollo_cli_src

    	npm install -D apollo

    	npx apollo help client

II. GENERATE APOLLO SOURCES

    $ gradlew generateApolloSources

    1. Problem with unamed queries

    	org.gradle.api.tasks.TaskExecutionException: Execution failed for task ':app:generateDebugServiceApolloSources'.

    	   [1]:{
    	   [2]:    allPersons {

    2. Solution: change query

     	   query GetAllPersonsQuery
    		{
    			allPersons {
    				id
    				name
    				species {
    					name
    				}
    				homeworld {
    					name
    				}
    			}
    		}

III. ANDROID QueryBuilder

    1. Problem: Query class does not have a .builder() method

    Query class does not have a .builder() method.

    It was described by @itarato and closed
    https://github.com/apollographql/apollo-android/issues/1866

    But the error is not resolved.

    2. Solution: Use graddle apollo porperty generateModelBuilder = true

    After try several solutions and read documentation I discovered that documentation needs to be fixed
    https://github.com/apollographql/apollo-android/blob/master/README.md

    // Replace:
    apollo {
    	generateKotlinModels.set(true)
    }

    // With:
    apollo {
    	generateModelBuilder = true
    }
