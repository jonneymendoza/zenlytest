# zenlytest
tech test code for zenly


Answer for Question 1:

Answer 1:

As the android:theme was introduced from 5.0 Android View class, All of the Appcomponent sub class views such as AppCompatButton, now has the ability to change the themes.

AppCompat has four levels of applying the theme for pre API 5.0

The info is a snippe readme file found in the appcompat library source code which can be found here https://android.googlesource.com/platform/frameworks/support/+/androidx-master-dev/appcompat/appcompat/THEMES?autodive=0 

The top level is the level us developers use to apply a theme that is from post 5.0 . in this case Theme.AppCompat  which is a sub resource from Theme.Base.AppCompat

In the 2nd level, Any post api 21 also derives from Theme.Base.AppCompat. This is so that devices dont use themes that are from  newer and unavailable platforms.

The third level inherits each platform specific the from the previous platform version so that Android can build  new themes on top of previous ones for backward compatibility.

The last level handles the base theme framework such as Theme.Holo which will be applied to all platform versions.

I had also found that the AppCompat derived view class(AppCompatEditText etc)  use a appcompat class called ThemeUtils that grabs the applied theme and tries to check if that theme is derived from a base AppCompat compatible theme via 

```ThemeUtils.checkAppCompatTheme(this, getContext());```

If the theme is derived from a appCompat theme, it will load it, else it will not.

