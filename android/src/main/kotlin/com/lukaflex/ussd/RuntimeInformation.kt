package com.lukaflex.ussd

import android.app.Activity

class RuntimeInformation {
    private var activityStore: Activity? = null
    constructor(activityStore: Activity?) {
        this.activityStore = activityStore
    }

}