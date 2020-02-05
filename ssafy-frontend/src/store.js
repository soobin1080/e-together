import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    accessToken: localStorage.getItem('accessToken'),
    user: localStorage.getItem('user'),
    email: localStorage.getItem('email'),
    personnel: '', // 인원
    budget: '', // 예산 
    tokenType: '',
    
  

  },
  getters : {
    isLoggedIn: function(state) {
      return state.accessToken ? true : false
    },
    requestHeader : function(state) {
      return {
      headers: {
          'Authorization': 'Bearer ' + state.accessToken
        }
      }
    },
    userId: function(state) {
      return state.user ? state.user : ""
    }
  },
  mutations : {
    setToken: function(state, token){
      state.accessToken = token
     // state.user = user
      //state.tokenType = 'Bearer'
    }

  },

  actions : {
 
  login: function(options, token) {
    options.commit('setToken', token)
  },

  logout: function(options) {
    options.commit('setToken')
  },
 

}
})