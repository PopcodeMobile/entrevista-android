package br.com.starwarswiki.middlewares

import android.content.Context
import br.com.starwarswiki.actions.ActionCreator
import br.com.starwarswiki.actions.Actions
import br.com.starwarswiki.models.AppState
import br.com.starwarswiki.models.Planet
import br.com.starwarswiki.services.SWServiceApi.getPlanets
import com.github.raulccabreu.redukt.actions.Action
import com.github.raulccabreu.redukt.middlewares.BeforeAction

class PlanetsMiddleware(context: Context) : NetworkOnMiddleware(context) {

    @BeforeAction(Actions.SYNC_PLANETS)
    fun synPlanets(state: AppState, action: Action<*>) {
        val list: MutableList<Planet> = mutableListOf()
        getPlanets() { planets, _ ->
            if (planets != null) list.addAll(planets.results)
        }
        ActionCreator.saveResponsePlanet(list)
    }
}