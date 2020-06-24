package com.challenge.kotlin.ravn

import com.challenge.kotlin.ravn.GetAllPersonsQuery.*
import com.challenge.kotlin.ravn.PersonDetailsQuery.Vehicle
import com.challenge.kotlin.ravn.activity.util.StringUtil
import java.util.*

object GraphQLUtil {
    fun getSpecies(species: List<Species>?): String {
        val sbuilder = StringBuilder()
        if (species == null) {
            return ""
        }
        for (index in species.indices) {
            val specie = species[index]
            sbuilder.append(specie.name())
            if (index < species.size - 1) {
                sbuilder.append(", ")
            }
        }
        return sbuilder.toString()
    }

    fun getHomeWorld(homeworld: Homeworld?): String {
        return homeworld?.name ?: ""
    }

    fun getVehices(vehicles: List<Vehicle>?): List<String?> {
        val values: MutableList<String?> =
            ArrayList()
        if (vehicles == null) {
            return values
        }
        for (index in vehicles.indices) {
            val vehicle = vehicles[index]
            values.add(
                StringUtil.formatGraphQLQueryText(
                    vehicle.name
                )
            )
        }
        return values
    }

    fun getPersonDescription(item: AllPerson, strFrom: String?): String {
        val sbuilder = StringBuilder()
        val specie = getSpecies(item.species)
        val homeworld = getHomeWorld(item.homeworld)
        if (specie != null && !specie.isEmpty()) {
            sbuilder.append(specie)
        }
        if (homeworld != null && !homeworld.isEmpty()) {
            sbuilder.append(" ").append(strFrom).append(" ").append(homeworld)
        }
        return sbuilder.toString()
    }
}