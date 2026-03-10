package com.example.myapplication.lab5.data

import com.example.myapplication.R
import com.example.myapplication.lab5.model.Dog


object DogRepository {
    val dogs = listOf(
        Dog(
            imageResourceId = R.drawable.koda,
            name = R.string.dog_name_1,
            age = 2,
            hobbies = R.string.dog_description_1
        ),
        Dog(
            imageResourceId = R.drawable.lola,
            name = R.string.dog_name_2,
            age = 16,
            hobbies = R.string.dog_description_2
        ),
        Dog(
            imageResourceId = R.drawable.frankie,
            name = R.string.dog_name_3,
            age = 2,
            hobbies = R.string.dog_description_3
        ),
        Dog(
            imageResourceId = R.drawable.nox,
            name = R.string.dog_name_4,
            age = 8,
            hobbies = R.string.dog_description_4
        ),
        Dog(
            imageResourceId = R.drawable.faye,
            name = R.string.dog_name_5,
            age = 8,
            hobbies = R.string.dog_description_5
        ),
        Dog(
            imageResourceId = R.drawable.bella,
            name = R.string.dog_name_6,
            age = 14,
            hobbies = R.string.dog_description_6
        ),
        Dog(
            imageResourceId = R.drawable.moana,
            name = R.string.dog_name_7,
            age = 2,
            hobbies = R.string.dog_description_7
        ),
        Dog(
            imageResourceId = R.drawable.tzeitel,
            name = R.string.dog_name_8,
            age = 7,
            hobbies = R.string.dog_description_8
        ),
        Dog(
            imageResourceId = R.drawable.leroy,
            name = R.string.dog_name_9,
            age = 4,
            hobbies = R.string.dog_description_9
        )
    )
}