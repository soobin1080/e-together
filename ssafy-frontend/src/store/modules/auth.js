const state = {
  tokenType : null,
  accessToken: null,
  //loading: false,
  user: null,
}

// data(state)를 변경하지 않음
// data를 원본 그대로 혹은 가공된 데이터를 사용
const getters = {
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
}

// mutation은 2개의 인자를 받는다.
// mutations의 동기적 함수들은 state의 상태를 변경시키는 동기적 함수이다 // 

const mutations = {
   // commit은 첫번째 인자로 mutations에 정의한 함수를 인자로 받는다.

  // 두번째 인자로는 token을 받아서 mutations에 정의된 함수를 통해 state를 변경한다.

  setToken: function(state, token){
    state.accessToken = token
  },
  // setLoading: function(state, status){
  //   state.loading = status
  // }
}

// 비동기 로직(axios로 django 서버에 로그인, 로그아웃을 요청)
const actions = {
 
  login: function(options, token) {
    options.commit('setToken', token)
  },

  logout: function(options) {
    options.commit('setToken')
  },

  // startLoading: function(options) {
  //   options.commit('setLoading', true)
  // },

  // endLoading: function(options) {
  //   options.commit('setLoading', false)
  // }


}

export default {
  state,
  getters,
  mutations,
  actions,
}