package com.kostyarazboynik.pokemon.dagger

import android.app.Application
import android.content.Context
import com.kostyarazboynik.feature_pokemon_details.dagger.FeaturePokemonDetailsUiComponent
import com.kostyarazboynik.feature_pokemon_details.dagger.FeaturePokemonDetailsUiModule
import com.kostyarazboynik.feature_pokemon_list.dagger.FeaturePokemonListUiComponent
import com.kostyarazboynik.feature_pokemon_list.dagger.FeaturePokemonListUiModule
import com.kostyarazboynik.pokemon.MainActivity
import com.kostyarazboynik.pokemon.PokemonApp
import com.kostyarazboynik.pokemon.dagger.module.BuildTypeModule
import com.kostyarazboynik.pokemon.dagger.module.PokemonApiModule
import com.kostyarazboynik.pokemon.dagger.module.RepositoryModule
import com.kostyarazboynik.pokemon.dagger.module.UseCasesModule
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        BuildTypeModule::class,
        FeaturePokemonListUiModule::class,
        FeaturePokemonDetailsUiModule::class,
        PokemonApiModule::class,
        RepositoryModule::class,
        UseCasesModule::class,
    ]
)
interface AppComponent {

    fun inject(app: PokemonApp)

    fun inject(activity: MainActivity)

    fun featurePokemonDetailsUiComponent(): FeaturePokemonDetailsUiComponent.Factory

    fun featurePokemonListUiComponent(): FeaturePokemonListUiComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun componentScope(componentScope: CoroutineScope): Builder

        fun build(): AppComponent
    }

    companion object : ScopeComponentProvider<AppComponent>() {

        fun init(
            application: Application,
        ) = store(
            coroutineScopeName = "AppComponent"
        ) { componentScope ->
            DaggerAppComponent
                .builder()
                .componentScope(componentScope)
                .context(application)
                .application(application)
                .build()
        }
    }
}
