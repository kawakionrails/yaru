package io.github.kawakionrails.yaru.data.models

data class RandomUserResponse(
    val results: List<RandomUser>,
    val info: UserInfo
)

data class RandomUser(
    val gender: String,
    val name: UserName,
    val location: UserLocation,
    val email: String,
    val login: UserLogin,
    val dob: UserDateOfBirth,
    val registered: UserRegistered,
    val phone: String,
    val cell: String,
    val id: UserId,
    val picture: UserProfilePicture,
    val nat: String
)

data class UserName(
    val title: String,
    val first: String,
    val last: String
)

data class UserLocation(
    val street: UserStreet,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: UserCoordinates,
    val timezone: UserTimezone
)

data class UserStreet(
    val number: Int,
    val name: String
)

data class UserCoordinates(
    val latitude: String,
    val longitude: String
)

data class UserTimezone(
    val offset: String,
    val description: String
)

data class UserLogin(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)

data class UserDateOfBirth(
    val date: String,
    val age: Int
)

data class UserRegistered(
    val date: String,
    val age: Int
)

data class UserId(
    val name: String,
    val value: String
)

data class UserProfilePicture(
    val large: String,
    val medium: String,
    val thumbnail: String
)

data class UserInfo(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)