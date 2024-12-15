package com.example.trainings.domain.models

enum class MuscleGroup(val displayName : String , id : Int) {
    CHEST("Грудные мышцы",0),
    BACK("Спинные мышцы",1),
    SHOULDERS("Плечевые мышцы",2),
    ARMS("Руки",3),
    LEGS("Ноги",4),
    ABS("Пресс",5),
    GLUTES("Ягодицы",6)
}