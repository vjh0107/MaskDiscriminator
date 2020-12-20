package com.vjh0107.kjSW

import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
data class Report(
    var imageLink: String? = "",
    var time: String? = "",
    var name: String? = ""
)