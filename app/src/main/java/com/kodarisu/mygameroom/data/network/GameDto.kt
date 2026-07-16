package com.kodarisu.mygameroom.data.network

data class GameDto(
    val id: Int,
    val name: String,
    val cover: CoverDto?,
    val genres: List<NamedReferenceDto>?,
    val platforms: List<NamedReferenceDto>?,
    val involved_companies: List<InvolvedCompanyDto>?,
    val rating: Double?,
    val hypes: Int?
)

data class  CoverDto(
    val id: Int,
    val url: String
)

data class InvolvedCompanyDto(
    val id: Int,
    val company: NamedReferenceDto?
)

data class NamedReferenceDto(
    val id: Int,
    val name: String
)
