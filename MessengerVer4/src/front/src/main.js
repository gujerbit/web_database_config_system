import { createApp } from 'vue'
import App from './App.vue'
import router from './router';
import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8090';

const APP = createApp(App);
const STORAGE = window.sessionStorage;
const DOMAIN = 'http://localhost:8090';
APP.config.globalProperties.domain = DOMAIN;
APP.config.globalProperties.storage = STORAGE;
APP.config.globalProperties.axios = axios;
APP.use(router).mount('#app');
