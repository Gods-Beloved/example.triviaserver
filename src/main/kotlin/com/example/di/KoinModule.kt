package com.example.di

import com.example.data.repository.AdvertDataSourceImpl
import com.example.data.repository.QuestionDataSourceImpl
import com.example.data.repository.TransactionDataSourceImpl
import com.example.data.repository.UserDataSourceImpl
import com.example.domain.repository.AdvertDataSource
import com.example.domain.repository.QuestionsDataSource
import com.example.domain.repository.TransactionsDataSource
import com.example.domain.repository.UserDataSource
import com.example.util.Constants.DATABASE_NAME
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val koinModule = module {

   // val mongoPW = System.getenv("MONGO_PW")

    single{
        KMongo.createClient(

            //Environment variable which we will create later from heroku

"mongodb+srv://triveagames:MlmDDuDUR4DJ9TJw@cluster0.d5vz4sb.mongodb.net/?retryWrites=true&w=majority"
           // connectionString = "mongodb+srv://Godwill:$mongoPW@cluster0.fyx16uq.mongodb.net/trivious_database?retryWrites=true&w=majority"
        ).coroutine.getDatabase(DATABASE_NAME)
    }

    single<UserDataSource> {
        UserDataSourceImpl(get())
    }

    single<QuestionsDataSource> {
        QuestionDataSourceImpl(get())
    }
    single<TransactionsDataSource> {
        TransactionDataSourceImpl(get())
    }

    single<AdvertDataSource>{
        AdvertDataSourceImpl(get())
    }


}