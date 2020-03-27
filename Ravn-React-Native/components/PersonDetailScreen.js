import React, { Component } from "react";
import {
  ScrollView,
  StyleSheet,
  ActivityIndicator,
  View,
  Text
} from "react-native";
import { Card, Button } from "react-native-elements";
import gql from "graphql-tag";
import { Query, Mutation } from "react-apollo";

const GET_PEOPLE = gql`
  query GetPeople {
    Person(id: "cj0nv9p8yewci0130wjy4o5fa") {
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

class PersonDetailScreen extends Component {
  static navigationOptions = {
    title: "Person Details"
  };
  render() {
    const { navigation } = this.props;
    return (
      <Query
        pollInterval={500}
        query={GET_PEOPLE}
        variables={{ id: navigation.getParam("id") }}
      >
        {({ loading, error, data }) => {
          if (loading)
            return (
              <View style={styles.activity}>
                <ActivityIndicator size="large" color="#0000ff" />
              </View>
            );
          if (error) return <Text>`Error! ${error.message}`</Text>;
          return (
            <ScrollView>
              <Card style={styles.container}>
                <View style={styles.subContainer}>
                  <View>
                    <Text
                      style={{
                        fontSize: 16,
                        fontWeight: "bold",
                        alignContent: "center"
                      }}
                    >
                      NAME: {data.Person.name}
                    </Text>
                  </View>
                </View>
                <View style={styles.subContainer}>
                  <View>
                    <Text style={{ fontSize: 18, fontWeight: "bold" }}>
                      GENERAL INFORMATION
                    </Text>
                  </View>
                </View>
                <View style={styles.subContainer}>
                  <View>
                    <Text style={{ fontSize: 16 }}>
                      Eye Color: {data.Person.eyeColor}
                      {data.Person.eyeColor}
                    </Text>
                    <Text style={{ fontSize: 16 }}>
                      Hair Color: {data.Person.eyeColor}
                      {data.Person.hairColor}
                    </Text>
                    <Text style={{ fontSize: 16 }}>
                      Skin Color: {data.Person.eyeColor}
                      {data.Person.skinColor}
                    </Text>
                    <Text style={{ fontSize: 16 }}>
                      Birth Year: {data.Person.eyeColor}
                      {data.Person.birthYear}
                    </Text>
                  </View>
                </View>
                <View style={styles.subContainer}>
                  <View>
                    <Text style={{ fontSize: 18, fontWeight: "bold" }}>
                      VEHICLES
                    </Text>
                  </View>
                </View>

                {data.Person.vehicles.map(vehicle => (
                  <div key={vehicle.id}>
                    <View>
                      <Text style={{ fontSize: 16 }}>{vehicle.name}</Text>
                    </View>
                  </div>
                ))}
              </Card>
            </ScrollView>
          );
        }}
      </Query>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20
  },
  subContainer: {
    flex: 1,
    paddingBottom: 20,
    borderBottomWidth: 2,
    borderBottomColor: "#CCCCCC"
  },
  activity: {
    position: "absolute",
    left: 0,
    right: 0,
    top: 0,
    bottom: 0,
    alignItems: "center",
    justifyContent: "center"
  },
  detailButton: {
    marginTop: 10
  }
});

export default PersonDetailScreen;
