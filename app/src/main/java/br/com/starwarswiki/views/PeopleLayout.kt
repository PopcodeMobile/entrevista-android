package br.com.starwarswiki.views

import android.content.Context
import br.com.starwarswiki.activities.PersonDetailActivity
import br.com.starwarswiki.models.AppState
import br.com.starwarswiki.models.Person
import br.com.starwarswiki.views.person.personSummaryView
import trikita.anvil.Anvil
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.*
import trikita.anvil.RenderableAdapter

class PeopleLayout(context: Context): ReactRenderableView(context){

    private var people = listOf<Person>()

    override fun view() {
        val adapter = RenderableAdapter.withItems(people) { _, person ->
            frameLayout {
                personSummaryView {
                    person(person)
                }
            }
        }

        listView {
            size(MATCH, MATCH)
            adapter(adapter)
            onItemClick { _, _, pos, _ ->
                PersonDetailActivity.open(context, people[pos].name)
            }
        }
    }

    override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
        return newState.people?.map { it.name } != oldState.people?.map { it.name }
    }

    override fun onChanged(state: AppState) {
        people = state.people ?: listOf()
        Anvil.render()
    }

}