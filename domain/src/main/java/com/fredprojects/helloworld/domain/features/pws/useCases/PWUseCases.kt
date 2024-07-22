package com.fredprojects.helloworld.domain.features.pws.useCases

import com.fredprojects.helloworld.domain.features.pws.useCases.crud.*

//Скопируйте часть Model из результата практической работы «Особенности ООП в Kotlin», реализуйте интерфейс с тем же функционалом в Android (Jetpack Compose) с использованием паттерна MVVM.
//Реализуйте использование Hilt для реализации Dependency Injection в результате прошлой практической работы
//В данной работе модифицируется результат практической работы «Dependency Injection» (Android).
//1. внедрите сохранение результатов между сеансами работи с использованием Room;
//2. внедрите возможность осуществлять прикрепление фотографии к хранимым сущностям, фотографии сохраняйте в файловой системе, а ссылки на них – в базе данных (посредством Room).
data class PWUseCases(
    val getPWs: GetPWUseCase,
    val upsert: UpsertPWUseCase,
    val delete: DeletePWUseCase
)