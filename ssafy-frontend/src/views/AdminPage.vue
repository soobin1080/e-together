<template>
  <div>
    <v-container>
      <v-text-field
        label="유저 검색"
        width="100px"
        v-model="userInfo"
        prepend-inner-icon="search"
        @keyup="searchUser"
      ></v-text-field>
    </v-container>
    <UserList :propsUserList="propsUserList">
    </UserList>
  </div>
</template>

<script>
import http from "../http-common";
import UserList from "../components/UserList";
export default {
  name: "AdminPage",
  components: {
    UserList
  },
  methods: {
    searchUser() {
      if (this.userInfo !== "") {
        this.propsUserList = this.userList.filter( user => {
          return (user.email.includes(this.userInfo) || user.name.includes(this.userInfo))
        }) 
      } else {
        this.propsUserList = this.userList
      }
    },
    getUserList() {
      const authentication = sessionStorage.getItem('auth')
      if (authentication !== "3") {
        alert('관리자만 접근 가능합니다.')
        this.$router.push('/')
      } else {
        http
          .get('/users', {
            params : {
              auth: authentication
            }
          }, this.$store.getters.requestHeader)
          .then(res => {
            console.log(res)
            this.userList = res.data
            this.propsUserList = res.data
          })
          .catch( err =>{
            console.log(err)
          })
      }
    }
  },

  data() {
    return {
      userList : [],
      propsUserList : [],
      userInfo: "",
    };
  },
  
  mounted() {
    this.getUserList()
  }
};
</script>
<style scoped>

</style>
