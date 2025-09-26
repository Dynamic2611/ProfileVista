# ProfileVista

Android app that displays a list of dummy users with details using **MVVM architecture**, **ViewModel + LiveData**, and **Glide** for image loading.  
Supports pull-to-refresh and error handling.

## Features
- 📋 Show list of users in a RecyclerView  
- 🔄 Pull to refresh (SwipeRefreshLayout)  
- 👤 User detail screen with profile, company, address, and bank info  
- 🌐 Fetch users from network via ViewModel  
- 🎨 Material Design UI with CardView  

## Tech Stack
- Java (Android)  
- ViewModel & LiveData  
- Retrofit (for API calls)  
- Glide (for images)  
- RecyclerView + CardView  
- SwipeRefreshLayout  

## Screens
- **Main Screen** → List of Users  
- **Detail Screen** → User Information  

## How to Run
1. Clone the repo  
2. Open in **Android Studio**  
3. Let Gradle sync  
4. Run on emulator/device  

## Placeholders
If resources are missing:  
- Add `ic_user_placeholder.xml` in `res/drawable/` (default avatar)  
- Add `ic_chevron_right.xml` in `res/drawable/` (arrow icon for list items)  

