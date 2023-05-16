package com.example.data.repository

import com.example.domain.model.questions.*
import com.example.domain.repository.QuestionsDataSource
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class QuestionDataSourceImpl(
    database: CoroutineDatabase

):QuestionsDataSource {

    private val questions = database.getCollection<Question>()


    override suspend fun getSportQuestion(): Sports? {
        val availableQuestion = mutableListOf<Sports?>()

        questions.findOne()?.sports?.forEach { item ->
            if (!item.received) {
                availableQuestion.add(
                    Sports(
                        question = item.question,
                        options = item.options,
                        answer = item.answer,
                    )
                )
            }
        }


        return availableQuestion.randomOrNull()


        // return questions.findOne()?.live?.get(0)?.sports?.toList()
    }

    override suspend fun updateSportQuestion(question: String):Boolean {

       return questions.updateOne(
            (Question::sports / Sports::question) eq question,
            setValue(Question::sports.posOp / Sports::received, true)
        ).wasAcknowledged()

    }

    override suspend fun getEntertainmentQuestion(): Entertainment? {
        val availableQuestion = mutableListOf<Entertainment?>()

        questions.findOne()?.entertainment?.forEach { item ->
            if (!item.received) {
                availableQuestion.add(
                    Entertainment(
                        question = item.question,
                        options = item.options,
                        answer = item.answer,
                    )
                )
            }
        }


        return availableQuestion.randomOrNull()






    }

    override suspend fun updateEntertainmentQuestion(question: String): Boolean {

        return questions.updateOne(
            (Question::entertainment / Entertainment::question) eq question,
            setValue(Question::entertainment.posOp / Entertainment::received, true)
        ).wasAcknowledged()

    }

    override suspend fun getAcademicQuestion(): Academic? {
        val availableQuestion = mutableListOf<Academic?>()

        questions.findOne()?.academic?.forEach { item ->
            if (!item.received) {
                availableQuestion.add(
                    Academic(
                        question = item.question,
                        options = item.options,
                        answer = item.answer,
                    )
                )
            }
        }


        return availableQuestion.randomOrNull()



    }

    override suspend fun getLiveQuestion(): General? {
        val availableQuestion = mutableListOf<General?>()

        questions.findOne()?.general?.forEach { item ->
            if (!item.received) {
                availableQuestion.add(
                    General(
                        question = item.question,
                        options = item.options,
                        answer = item.answer,
                    )
                )
            }
        }


        return availableQuestion.randomOrNull()


    }

    override suspend fun updateLiveQuestion(question: String): Boolean {
        return  questions.updateOne(
            (Question::general / General::question) eq question,
            setValue(Question::general.posOp / General::received, true)
        ).wasAcknowledged()

    }

    override suspend fun getLivePrice(): FeeSizeResponse{
        val fee = questions.findOne()?.entry?.generalPrice
        val winnings = questions.findOne()?.entry?.generalWinnings

        val availableQuestion = mutableListOf<General?>()

        questions.findOne()?.general?.forEach {
                item ->
            if (!item.received){
                availableQuestion.add(
                    General(
                        question = item.question,
                        options = item.options,
                        answer = item.answer,
                    )
                )
            }
        }

        val size = availableQuestion.size

        return if (availableQuestion.size < 1){
            FeeSizeResponse(
                size = size,
                entryFee = fee,
                success = true,
                winningFee = winnings
            )
        }else{
            FeeSizeResponse(
                size = size,
                entryFee = fee!!,
                success = false,
                winningFee = winnings


            )
        }



    }

    override suspend fun updateAcademicQuestion(question: String): Boolean {
        return  questions.updateOne(
            (Question::academic / Academic::question) eq question,
            setValue(Question::academic.posOp / Academic::received, true)
        ).wasAcknowledged()

    }

    override suspend fun getSportPrice(): FeeSizeResponse {

        val fee = questions.findOne()?.entry?.sportsPrice
        val winnings = questions.findOne()?.entry?.sportsWinnings

        val availableQuestion = mutableListOf<Sports?>()

        questions.findOne()?.sports?.forEach {
                item ->
            if (!item.received){
                availableQuestion.add(
                    Sports(
                        question = item.question,
                        options = item.options,
                        answer = item.answer,
                    )
                )
            }
        }

        val size = availableQuestion.size

        return if (availableQuestion.size < 1){
            FeeSizeResponse(
                size = size,
                entryFee = fee,
                success = true,
                winningFee = winnings
            )
        }else{
            FeeSizeResponse(
                size = size,
                entryFee = fee!!,
                success = false,
                winningFee = winnings


            )
        }






    }

    override suspend fun getEntertainmentPrice(): FeeSizeResponse {

        val fee = questions.findOne()?.entry?.entertainmentPrice
        val winnings = questions.findOne()?.entry?.entertainmentWinnings

        val availableQuestion = mutableListOf<Entertainment?>()

        questions.findOne()?.entertainment?.forEach {
                item ->
            if (!item.received){
                availableQuestion.add(
                    Entertainment(
                        question = item.question,
                        options = item.options,
                        answer = item.answer,
                    )
                )
            }
        }

        val size = availableQuestion.size

        return if (availableQuestion.size < 1){
            FeeSizeResponse(
                size = size,
                entryFee = fee,
                success = true,
                winningFee = winnings

            )
        }else{
            FeeSizeResponse(
                size = size,
                entryFee = fee!!,
                success = false,
                winningFee = winnings


            )
        }


    }

    override suspend fun getAcademicPrice(): FeeSizeResponse {
        val fee = questions.findOne()?.entry?.academicPrice
        val winnings = questions.findOne()?.entry?.academicWinnings

        val availableQuestion = mutableListOf<Academic?>()

        questions.findOne()?.academic?.forEach {
                item ->
            if (!item.received){
                availableQuestion.add(
                    Academic(
                        question = item.question,
                        options = item.options,
                        answer = item.answer,
                    )
                )
            }
        }

        val size = availableQuestion.size

        return if (availableQuestion.size > 0){
            FeeSizeResponse(
                size = size,
                entryFee = fee!!,
                success = true,
                winningFee = winnings!!

            )
        }else{
            FeeSizeResponse(
                size = size,
                entryFee = fee!!,
                success = false,
                winningFee = winnings!!


            )
        }


    }


}