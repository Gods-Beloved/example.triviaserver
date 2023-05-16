package com.example.domain.repository

import com.example.domain.model.user.account_info.Account

interface TransactionsDataSource {

    suspend fun saveTransaction(account: Account,userId:String, userDataSource:UserDataSource, amount:String):Boolean


    suspend fun getOnlyWithdrawal(id:String):List<Account>?

    suspend fun getOnlyDeposit(id:String):List<Account>?

    suspend fun getAllAppTransactions(id:String):List<Account>?





}