import Vue from 'vue'
import Vuex from 'vuex'
import Vuetify from 'vuetify/lib'
import 'vuetify/dist/vuetify.min.css'
import VueSimplemde from 'vue-simplemde'
import 'simplemde/dist/simplemde.min.css'
import 'font-awesome/css/font-awesome.min.css'
import VModal from 'vue-js-modal'
import VueAxios from 'vue-axios'
import InfiniteLoading from 'vue-infinite-loading';
import App from './App.vue'
import router from './router'
import store from './store'
import VueCsrf from 'vue-csrf';
import VueSessionStorage from 'vue-sessionstorage'
import UploadImage from 'vue-upload-image';
import './registerServiceWorker'

import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import '@mdi/font/css/materialdesignicons.css'
import VueSession from 'vue-session'

import VuetifyNumberInput from '@jzolago/vuetify-number-input'

var infiniteScroll =  require('vue-infinite-scroll');
Vue.use(infiniteScroll)
Vue.use(InfiniteLoading, { /* options */ });
Vue.config.productionTip = false

Vue.use(VueSimplemde)
Vue.use(Vuetify)
Vue.use(Vuex)

Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)
Vue.use(VModal)
Vue.use(VueCsrf);
Vue.use(VueAxios, axios)
Vue.use(VuetifyNumberInput);
Vue.use(VueSession, {persist: true})
Vue.use(VueSessionStorage)

// Require dependencies
Vue.component('upload-image', UploadImage)


export const eventBus = new Vue()

new Vue({
  router,
	store,
	vuetify: new Vuetify({
		iconfont: 'fa',
		theme: {
			primary: '#3f51b5',
			secondary: '#b0bec5',
			accent: '#8c9eff',
			error: '#b71c1c'
		}
	}),
  render: h => h(App)
}).$mount('#app')
