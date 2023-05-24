package com.crocodic.core.helper

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SessionHelper(val context: Context) {

    private lateinit var preferences: SharedPreferences

    /**
     * File name of session
     * @param name The name of preference
     */
    fun setName(name: String) {
        if (name.isEmpty()) throw RuntimeException("name must be required")
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    /**
     * Retrieve a String value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return The preference value if it exists, or defValue.
     * @throws ClassCastException If there is a preference with this name that is not a String
     */
    fun get(key: String, defValue: String? = null): String? {
        return preferences.getString(key, defValue)
    }

    /**
     * Retrieve a boolean value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     * @throws ClassCastException If there is a preference with this name that is not a boolean.
     */
    fun get(key: String, defValue: Boolean = false): Boolean {
        return preferences.getBoolean(key, defValue)
    }

    /**
     * Retrieve a float value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return The preference value if it exists, or defValue.
     * @throws ClassCastException If there is a preference with this name that is not a float.
     */
    fun get(key: String, defValue: Float = 0.0f): Float {
        return preferences.getFloat(key, defValue)
    }

    /**
     * Retrieve a double value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     * @throws ClassCastException If there is a preference with this name that is not a long.
     */
    fun get(key: String, defValue: Double = 0.0): Double {
        key.let {
            if (preferences.contains(it)) {
                val bits = preferences.getLong(it, 0)
                return java.lang.Double.longBitsToDouble(bits)
            }
        }
        return defValue
    }

    /**
     * Retrieve an int value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     * @throws ClassCastException If there is a preference with this name that is not an int.
     */
    fun get(key: String, defValue: Int = 0): Int {
        return preferences.getInt(key, defValue)
    }

    /**
     * Retrieve a long value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return The preference value if it exists, or defValue.
     * @throws ClassCastException If there is a preference with this name that is not a long.
     */
    fun get(key: String, defValue: Long = 0L): Long {
        return preferences.getLong(key, defValue)
    }

    /**
     * Retrieve a set of String values from the preferences.
     *
     * Note that you *must not* modify the set instance returned by this call. The consistency of the stored
     * data is not guaranteed if you do, nor is your ability to modify the instance at all.
     *
     * @param key The name of the preference to retrieve.
     * @param defValues Values to return if this preference does not exist.
     * @return The preference values if they exist, or defValues.
     * @throws ClassCastException If there is a preference with this name that is not a Set.
     */
    fun get(key: String, defValues: Set<String>? = null): Set<String>? {
        return preferences.getStringSet(key, defValues)
    }

    /**
     * This atomically performs the requested modifications, replacing whatever is currently in the SharedPreferences.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun save(key: String, value: Boolean) {
        edit().put(key, value).apply()
    }

    /**
     * This atomically performs the requested modifications, replacing whatever is currently in the SharedPreferences.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun save(key: String, value: Float) {
        edit().put(key, value).apply()
    }

    /**
     * This atomically performs the requested modifications, replacing whatever is currently in the SharedPreferences.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun save(key: String, value: Double) {
        edit().put(key, value).apply()
    }

    /**
     * This atomically performs the requested modifications, replacing whatever is currently in the SharedPreferences.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun save(key: String, value: Int) {
        edit().put(key, value).apply()
    }

    /**
     * This atomically performs the requested modifications, replacing whatever is currently in the SharedPreferences.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun save(key: String, value: Long) {
        edit().put(key, value).apply()
    }

    /**
     * This atomically performs the requested modifications, replacing whatever is currently in the SharedPreferences.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun save(key: String, value: Set<String>?) {
        edit().put(key, value).apply()
    }

    /**
     * This atomically performs the requested modifications, replacing whatever is currently in the SharedPreferences.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun save(key: String, value: String) {
        edit().put(key, value).apply()
    }

    /**
     * Removes a preference from the SharedPreferences.
     *
     * @param key The name of the preference to remove.
     */
    fun remove(key: String) {
        edit().remove(key).apply()
    }

    /**
     * Checks whether the preferences contains a preference.
     *
     * @param key The name of the preference to check.
     * @return True if the preference exists in the preferences, otherwise false.
     */
    fun contains(key: String): Boolean {
        return preferences.contains(key)
    }

    /**
     * Increases the value of an int preference.
     *
     * @param key The name of the preference to modify.
     * @param increment The amount by which the preference should be increased.
     * @return The value after incrementing the preference.
     */
    fun increment(key: String, increment: Int = 1): Int {
        val value = get(key, 0) + increment
        save(key, value)
        return value
    }

    /**
     * Check if a preference key exists in the SharedPreferences. If it does not exist then the preference will be
     * saved with the boolean value "true". This is useful to check if something has already occurred.
     *
     * @param key The name of the preference to modify/check.
     * @return `true` if the preference has not yet been saved.
     */
    fun isFirstTime(key: String): Boolean {
        if (contains(key)) {
            return false
        }
        save(key, true)
        return true
    }

    /**
     * Retrieve all values from the preferences.
     *
     * Note that you *must not* modify the collection returned by this method, or alter any
     * of its contents. The consistency of your stored data is not guaranteed if you do.
     *
     * @return Returns a map containing a list of pairs key/value representing the preferences.
     * @throws NullPointerException
     */
    fun getAll(): Map<String, *> {
        return preferences.all
    }

    /**
     * Create a new Editor for these preferences, through which you can make modifications to the data in the
     * preferences and atomically commit those changes back to the SharedPreferences object.
     *
     * Note that you *must* call [Editor.commit] to have any changes you perform in the Editor actually
     * show up in the SharedPreferences.
     *
     * @return Returns a new instance of the [Editor], allowing you to modify the values in this
     * SharedPreferences object.
     */
    fun edit(): Editor {
        return Editor(this)
    }

    fun clearAll() {
        edit().clear()
    }


    /**
     * Interface used for modifying values in a [SharedPreferences] object. All changes you make in an editor are
     * batched, and not copied back to the original [SharedPreferences] until you call [commit] or
     * [apply]
     */
    @SuppressLint("CommitPrefEdits")
    class Editor internal constructor(session: SessionHelper) {

        private val editor = session.preferences.edit()

        /**
         * Set a String value in the preferences editor, to be written back once [commit] or [apply] are
         * called.
         *
         * @param key The name of the preference to modify.
         * @param value The new value for the preference.
         * @return A reference to the same Editor object, so you can chain put calls together.
         */
        fun put(key: String, value: String): Editor {
            editor.putString(key, value)
            return this
        }

        /**
         * Set a boolean value in the preferences editor, to be written back once [commit] or [apply] are
         * called.
         *
         * @param key The name of the preference to modify.
         * @param value The new value for the preference.
         * @return A reference to the same Editor object, so you can chain put calls together.
         */
        fun put(key: String, value: Boolean): Editor {
            editor.putBoolean(key, value)
            return this
        }

        /**
         * Set a float value in the preferences editor, to be written back once [commit] or [apply] are
         * called.
         *
         * @param key The name of the preference to modify.
         * @param value The new value for the preference.
         * @return A reference to the same Editor object, so you can chain put calls together.
         */
        fun put(key: String, value: Float): Editor {
            editor.putFloat(key, value)
            return this
        }

        /**
         * Set a double value in the preferences editor, to be written back once [commit] or [apply] are
         * called.
         *
         * @param key The name of the preference to modify.
         * @param value The new value for the preference.
         * @return A reference to the same Editor object, so you can chain put calls together.
         */
        fun put(key: String, value: Double): Editor {
            // http://stackoverflow.com/a/18098090/1048340
            editor.putLong(key, java.lang.Double.doubleToRawLongBits(value))
            return this
        }

        /**
         * Set an int value in the preferences editor, to be written back once [commit] or [apply] are
         * called.
         *
         * @param key The name of the preference to modify.
         * @param value The new value for the preference.
         * @return A reference to the same Editor object, so you can chain put calls together.
         */
        fun put(key: String, value: Int): Editor {
            editor.putInt(key, value)
            return this
        }

        /**
         * Set a long value in the preferences editor, to be written back once [commit] or [apply] are
         * called.
         *
         * @param key The name of the preference to modify.
         * @param value The new value for the preference.
         * @return A reference to the same Editor object, so you can chain put calls together.
         */
        fun put(key: String, value: Long): Editor {
            editor.putLong(key, value)
            return this
        }

        /**
         * Set a set of String values in the preferences editor, to be written back once [commit] or
         * [apply] is called.
         *
         * @param key The name of the preference to modify.
         * @param values The set of new values for the preference. Passing `null` for this argument is equivalent to calling
         * [remove] with this key.
         * @return A reference to the same Editor object, so you can
         * chain put calls together.
         */
        fun put(key: String, values: Set<String>?): Editor {
            editor.putStringSet(key, values)
            return this
        }

        /**
         * Mark in the editor that a preference value should be removed, which will be done in the actual preferences
         * once [commit] is called.
         *
         * Note that when committing back to the preferences, all removals are done first, regardless of whether you
         * called remove before or after put methods on this editor.
         *
         * @param key The name of the preference to remove.
         * @return A reference to the same Editor object, so you can chain put calls together.
         */
        fun remove(key: String): Editor {
            editor.remove(key)
            return this
        }

        /**
         * Commit your preferences changes back from this Editor to the [SharedPreferences] object it is editing.
         * This atomically performs the requested modifications, replacing whatever is currently in the SharedPreferences.
         *
         * Note that when two editors are modifying preferences at the same time, the last one to call apply wins.
         *
         * Unlike [commit], which writes its preferences out to persistent storage synchronously,
         * `apply` commits its changes to the in-memory [SharedPreferences] immediately but starts an
         * asynchronous commit to disk and you won't be notified of any failures.  If another editor on this
         * [SharedPreferences] does a regular [commit] while a `apply` is still outstanding, the
         * [commit] will block until all async commits are completed as well as the commit itself.
         *
         *
         * As [SharedPreferences] instances are singletons within a process, it's safe to replace any instance
         * of [commit] with `apply` if you were already ignoring the return value.
         *
         * You don't need to worry about Android component lifecycles and their interaction with `apply()`
         * writing to disk.  The framework makes sure in-flight disk writes from `apply()` complete before
         * switching states.
         *
         * The SharedPreferences.Editor interface isn't expected to be implemented directly. However, if
         * you previously did implement it and are now getting errors about missing `apply()`, you can simply
         * call [commit] from `apply()`.
         */
        fun apply() {
            editor.apply()
        }

        /**
         * Commit your preferences changes back from this Editor to the [SharedPreferences] object it is editing.
         * This atomically performs the requested modifications, replacing whatever is currently in the SharedPreferences.
         *
         * Note that when two editors are modifying preferences at the same time, the last one to call commit wins.
         *
         * If you don't care about the return value and you're using this from your application's main thread,
         * consider using [apply] instead.
         *
         * @return True if the new values were successfully written to persistent storage.
         */
        fun commit(): Boolean {
            return editor.commit()
        }

        fun clear() {
            editor.clear()
            apply()
        }


    }

    interface StringEncryptor {

        /**
         * Encrypt a String.
         *
         * @param plainText the String to encrypt.
         * @return The encrypted String.
         */
        fun encrypt(plainText: String): String

        /**
         * Decrypt a String.
         *
         * @param encryptedText The encrypted text using [decrypt].
         * @return The original String.
         */
        fun decrypt(encryptedText: String): String

    }

    /**
     * Obfuscate preference keys
     */
    interface Obfuscator {

        /**
         * Obfuscate a string.
         *
         * @param string
         * the original string
         * @return an obfuscated string.
         */
        fun obfuscate(string: String): String

    }

}