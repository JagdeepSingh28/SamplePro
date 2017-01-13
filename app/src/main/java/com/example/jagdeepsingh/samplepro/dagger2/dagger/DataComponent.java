package com.example.jagdeepsingh.samplepro.dagger2.dagger;

import com.example.jagdeepsingh.samplepro.dagger2.Activities.MainDaggerActivity;
import com.example.jagdeepsingh.samplepro.dagger2.fragments.FormDisplayFragment;
import com.example.jagdeepsingh.samplepro.dagger2.fragments.FormEditFragment;

import dagger.Component;

/**
 * Created by Jagdeep.Singh on 11-01-2017.
 */

/**
 * * This component links
 * DataModule ( where we have
 * defined way to instantiate
 * dependent object DataService )
 * and dependent classes which are
 * MainActivity,FormDisplayFragment
 * and FormEditFragment).
 */
@Component(modules = {DataModule.class})
public interface DataComponent {

    /**
     * * If component only knows about module
     * and not the dependent classes, then
     * there is no way for dependent classes
     * to use the dependency object. So
     * the following method act as link
     * between component and dependent classes.
     * This link enables the dependent classes
     * to use the dependency object or DataService
     * object
     * @param target
     */
    void inject(MainDaggerActivity target);

    void inject(FormDisplayFragment formDisplayFragment);

    void injectTo(FormEditFragment formEditFragment);
}
