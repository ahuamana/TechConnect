package com.ahuaman.client.data.firebase.di


import com.ahuaman.client.data.firebase.remote.FirebaseRemoteDataSourceImpl
import com.ahuaman.client.data.firebase.remote.FirebaseRemoteDataSource
import com.ahuaman.client.data.firebase.repository.FirebaseRepositoryImpl
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Singleton
    @Provides
    fun provideFirebase(): FirebaseFirestore {
        return Firebase.firestore
    }

    //users collection
    @UsersCollection
    @Singleton
    @Provides
    fun provideUsersCollection(db:FirebaseFirestore): CollectionReference {
        return db.collection("users")
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseModuleAbstract {

    @UsersCollection
    @Singleton
    @Provides
    abstract fun provideRemoteDataSource(firebaseRemoteDataSourceImpl: FirebaseRemoteDataSourceImpl): FirebaseRemoteDataSource

    @Singleton
    @Provides
    abstract fun provideRepository(remoteDataSource: FirebaseRepositoryImpl): FirebaseFirestore

}