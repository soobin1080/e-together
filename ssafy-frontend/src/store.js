import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
		accessToken: '',
    user: '',

    people: '',
    money: '',
    pro_name: '',
    price:''

    },
    computed:{
      people(){
        return this.$store.state.people
      }
    }
})
