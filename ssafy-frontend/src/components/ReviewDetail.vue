<template>
  <!-- <div class="container" style="height:100%; width:50%;"> -->
    <div class="card mb-3" style="height:100%;">
      <div class="row no-gutters">
        <div class="col-md-4">
          <img src="" class="card-img" alt="">
        </div>
        <div class="col-md-8">
          <div class="card-body mt-8">
            <h4 class="card-title text-center">{{budgetInfo.budget_num}} : {{budgetInfo.budget_title}}</h4>
            <div class="text-center">
              <p class="card-text">{{review.review_content}}</p>
              <p class="card-text"><small class="text-muted">{{dateParsing(review.review_date)}}</small></p>
              <i class="fas fa-heart text-danger mr-1 mb-3"></i>{{like_users.length}}
              <span
                id="likeBtn" 
                class="badge badge-pill badge-primary" 
                style="cursor:pointer"
                @click="like(review)">좋아요</span><br>
              <!-- <span v-else 
                class="badge badge-pill badge-primary" 
                style="cursor:pointer"
                @click="like(review)">좋아요</span> -->
              <p v-if="budgetInfo.suitability === 1" class="text-center d-inline-block bg-primary rounded">
                사용자가 적합하다고 판단한 예산입니다.
              </p>
              <p v-else-if="budgetInfo.suitability == 2" class="text-center bg-warning rounded">
                사용자가 부적합하다고 판단한 예산입니다.
              </p>
              <p v-else class="text-center bg-secondary rounded">
                사용자가 아직 적합도를 정하지 않았습니다.
              </p>
            </div>
            <p class="text-center font-weight-bolder">인원 : {{budgetInfo.personnel}} / 예산 : {{budgetInfo.budget}}</p>

            <p class="text-center" 
              data-toggle="modal" 
              data-target="#exampleModalLong" 
              style="cursor:pointer">
              예산 전체보기
          </p>
            <!-- <div v-for="budget in this.budgetList">
              {{ budget.pro_name }} * {{budget.quantity}} = {{budget.price}}
            </div> -->
          </div>
  

          <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLongTitle">장바구니 전체</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                  <ul class="list-group list-group-flush">
                    <li v-for="product in budgetList" class="list-group-item">
                      {{product.pro_name}} * {{product.quantity}}
                    </li>
                  </ul>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  <!-- </div> -->

  
</template>

<script>
import EventBus from "../event-bus"
import http from "../http-common";
  export default {
    name: "ReviewDetail",

    data() {
      return {
        review : Object,
        budgetInfo : Object,
        budgetList: Array,
        like_users : Array,
        reviewNum: Number,
        likeStatus: false,
        dialogm1: '',
        dialog: false,
      }
    },

    methods: {
      dateParsing(beforeParsing) {
        const t = beforeParsing.indexOf('T')
        const afterParsing = beforeParsing.substring(0, t)
        // console.log(afterParsing)
        return afterParsing 
      },
      getReviewDetailByArg(review_num) {
        let loginUser = sessionStorage.getItem('email')
        http
          .get(`/review/${this.reviewNum}`, 
          {review_num: this.reviewNum}, 
          this.$store.getters.requestHeader)
            .then(res => {
              console.log(res)
              this.review = res.data.review
              this.budgetInfo = res.data.budgetinfo
              this.budgetList = res.data.budgetlist
              this.like_users = res.data.like_user
              let loginUser = sessionStorage.getItem('email')
              var likeBtn = document.getElementById('likeBtn')
              console.log(likeBtn)
              if (this.like_users.includes(loginUser)) {
                likeBtn.innerHTML = "좋아요 취소"
                likeBtn.className = "badge badge-pill badge-danger"
              } else {
                likeBtn.innerHTML = "좋아요"
                likeBtn.className = "badge badge-pill badge-primary"
              }
            })
            .catch(err => {
              console.log(err)
            })
      },
      getReviewDetail() {
        this.reviewNum = this.$route.params.reviewNum
        console.log('getReviewDetail')
        http
          .get(`/review/${this.reviewNum}`, 
          {review_num: this.reviewNum}, 
          this.$store.getters.requestHeader)
            .then(res => {
              console.log(res)
              this.review = res.data.review
              this.budgetInfo = res.data.budgetinfo
              this.budgetList = res.data.budgetlist
              this.like_users = res.data.like_user
              let loginUser = sessionStorage.getItem('email')
              var likeBtn = document.getElementById('likeBtn')
              console.log(likeBtn)
              if (this.like_users.includes(loginUser)) {
                likeBtn.innerHTML = "좋아요 취소"
                likeBtn.className = "badge badge-pill badge-danger"
              } else {
                likeBtn.innerHTML = "좋아요"
                likeBtn.className = "badge badge-pill badge-primary"
              }
            })
            .catch(err => {
              console.log(err)
            })
      },

       like(review) {
        let loginUser = sessionStorage.getItem('email')
        if (loginUser === null || typeof loginUser === undefined) {
           alert('로그인이 필요합니다.')
           return
        }
        // console.log(review)
        if (this.like_users.includes(loginUser)) {
          console.log('delete')
          console.log(review.review_num)
          console.log(loginUser)
          http
            .delete(`/review/like_count/${this.review.review_num}/${loginUser}`
            , this.$store.getters.requestHeader)
            .then(res => {
              this.getReviewDetail(loginUser);
              console.log(res)
            })
            .catch( err => {
              console.log(err)
            })
        } else {
          console.log('like')
          http
            .post(`/review/like_count`,{
              review_num: review.review_num,
              user_email: loginUser
            }, this.$store.getters.requestHeader)
              .then(res => {
                console.log(res)
                this.getReviewDetailByArg(loginUser)
              })
              .catch(err => {
                console.log(err)
              })
        }
      },

    },
    mounted() {
      this.getReviewDetail();

    },

    computed: {
     convertDate() {
       console.log("convertDate")
       let convertDate = new Date(this.date)
       console.log(convertDate)
       return this.date
     },

     computedReviewNum() {
       return this.$route.params.reviewNum;
     }
    },

  }
</script>

<style scoped>

</style>