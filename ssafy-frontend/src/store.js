import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    accessToken: '',
    user: '',
    people: '', // 인원
    money: '', // 예산
    // productlist: [{ pro_name: '', price: '', quantity: '' }]
    pro_name: '', // 상품명
    price: '', // 가격
    quantity: '', // 수량
    tokenType: ''

  },
  getters : {
    isLoggedIn: function(state) {
      return state.accessToken ? true : false
    },
    requestHeader : function(state) {
      return {
      headers: {
          Authorization: 'Bearer ' + state.accessToken
        }
      }
    },
    userId: function(state) {
      
    }
  },
  mutations : {

    setToken: function(state, token){
      state.accessToken = token
     // state.user = user
      //state.tokenType = 'Bearer'
    },
  },

  actions : {
 
  login: function(options, token) {
    options.commit('setToken', token)
  },

  logout: function(options) {
    options.commit('setToken')
  },


},

  computed: {

  },

 
  
})
