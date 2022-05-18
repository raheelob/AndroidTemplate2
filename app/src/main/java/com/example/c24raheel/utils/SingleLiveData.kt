package com.example.c24raheel.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveData<T> : MutableLiveData<T>() {

    //stackoverflow answer: https://stackoverflow.com/questions/59834398/android-navigation-component-back-button-not-working#

    /**
     * Explanation...
     *  For anyone using LiveData in a previous Fragment which is a Home Fragment, whenever you go back to the previous Fragment
     *  by pressing back button the Fragment is starting to observe the data and because ViewModel survives this operation it
     *  immediately emits the last emitted value which in my case opens the Fragment from which I pressed the back button,
     *  that way it looks like the back button is not working the solution for this is using something that emits data only once.*/

    private val pending = AtomicBoolean()

    /**
     * Adds the given observer to the observers list within the lifespan of the given
     * owner. The events are dispatched on the main thread. If LiveData already has data
     * set, it will be delivered to the observer.
     *
     * @param owner The LifecycleOwner which controls the observer
     * @param observer The observer that will receive the events
     * @see MutableLiveData.observe
     */
    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    /**
     * Sets the value. If there are active observers, the value will be dispatched to them.
     *
     * @param value The new value
     * @see MutableLiveData.setValue
     */
    @MainThread
    override fun setValue(value: T?) {
        pending.set(true)
        super.setValue(value)
    }
}