importScripts('https://www.gstatic.com/firebasejs/9.23.0/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/9.23.0/firebase-messaging-compat.js');

firebase.initializeApp({
    apiKey: "AIzaSyDGBjOu4ekh_UBe4meHKZaN8FpelyrqUGI",
    authDomain: "pushnotification-53eea.firebaseapp.com",
    projectId: "pushnotification-53eea",
    storageBucket: "pushnotification-53eea.appspot.com",
    messagingSenderId: "625034588928",
    appId: "1:625034588928:web:78f7af81a2b2734eb3f9e5",
    measurementId: "G-321K20Y791"
});

const messaging = firebase.messaging();