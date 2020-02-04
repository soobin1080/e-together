import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    accessToken: localStorage.getItem('accessToken'),
    user: localStorage.getItem('user'),
    people: '', // 인원
    money: '', // 예산
    // productlist: [{ pro_name: '', price: '', quantity: '' }]
    pro_name: '', // 상품명
    price: '', // 가격
    quantity: '', // 수량
    tokenType: 'Bearer',
    
    list:{
      pro_name: '', // 상품명
      price: '', // 가격
      quantity: '' // 수량
    }

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
      return state.user ? user : ""
    }
  },
  mutations : {
    setToken: function(state, token){
      state.accessToken = token
    }
  },



  actions : {
 
    login: function(options, token, user) {
      options.commit('setToken', token)
    },

    logout: function(options) {
      options.commit('setToken')
    },
},

  computed: {
    
  },


})
