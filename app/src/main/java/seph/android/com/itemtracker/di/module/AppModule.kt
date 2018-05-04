package seph.android.com.itemtracker.di.module

import android.app.Application
import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import seph.android.com.itemtracker.data.source.firebase.FirebaseDatabase
import seph.android.com.itemtracker.data.source.firebase.FirebaseItemRepository
import javax.inject.Singleton

/**
 * Created by seph on 03/05/2018.
 */

@Module
class AppModule {

    // put all provider here

    @Provides
    fun provideContext(app : Application) : Context = app.applicationContext

    @Provides
    @Singleton
    fun provideFirebaseFirestore() : FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseDatabase(firebaseFirestore: FirebaseFirestore) : FirebaseDatabase = FirebaseDatabase(firebaseFirestore)

    @Provides
    @Singleton
    fun provideItemRepository(firebaseDatabase: FirebaseDatabase) : FirebaseItemRepository = FirebaseItemRepository(firebaseDatabase)
}