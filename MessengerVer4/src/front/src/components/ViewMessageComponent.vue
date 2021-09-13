<template>
  <div id="wrap">
      <div class="message-list" v-for="(value, index) in messageInfo" :key="value">
          <div class="title-area" v-if="index === 0">
              <p class="title">INDEX</p>
              <div class="title">ROOM</div>
              <p class="title">USER</p>
              <p class="title">CONTENT</p>
          </div>
          <div class="content-area">
              <p class="content">{{value.idx}}</p>
              <p class="content">{{value.room_id}}</p>
              <p class="content">{{value.id}}</p>
              <p class="content">{{value.message}}</p>
          </div>
      </div>
      <div class="page">
          <div class="prev" @click="prevPage()">&laquo;</div>
          <div class="current" v-for="value in currentPages[currentNumber]" :key="value">
              <p v-if="limit === value" class="select" @click="chaingePage(value)">{{value}}</p>
              <p v-else @click="chaingePage(value)">{{value}}</p>
          </div>
          <div class="next" @click="nextPage()">&raquo;</div>
      </div>
      <div class="search">
          <input type="text" placeholder="keword" @input="search($event)">
      </div>
  </div>
</template>

<script>
export default {
    data() {
        return {
            messageInfo: [],
            currentPages: [],
            currentNumber: 0,
            limit: 1,
            searchData: '',
        }
    },
    async mounted() {
        this.getMessage();
    },
    methods: {
        async getMessage() {
            let { data: messages } = await this.axios.get(`/view/view_message/${(this.limit - 1) * 10}`);
            this.messageInfo = messages;

            let { data: size } = await this.axios.get(`/view/view_message_size`);
            let maxPage = Math.ceil(size / 10);
            let temp = [];
            this.currentPages = [];

            for(let i = 1; i <= maxPage; i++) {
                temp.push(i);

                if(i % 5 === 0) {
                    this.currentPages.push(temp);
                    temp = [];
                } else if(i === maxPage) {
                    this.currentPages.push(temp);
                }
            }
        },
        chaingePage(value) {
            this.limit = value;
            
            if(this.searchData !== '') {
                this.search(null);
            } else {
                this.getMessage();
            }
        },
        prevPage() {
            if(this.currentNumber > 0) {
                this.currentNumber--;
                this.limit = this.currentPages[this.currentNumber][0];
                if(this.searchData !== '') {
                    this.search(null);
                } else {
                    this.getMessage();
                }
            }
        },
        nextPage() {
            if(this.currentNumber < this.currentPages.length - 1) {
                this.currentNumber++;
                this.limit = this.currentPages[this.currentNumber][0];
                if(this.searchData !== '') {
                    this.search(null);
                } else {
                    this.getMessage();
                }
            }
        },
        async search(event) {
            if(event !== null) {
                this.searchData = event.currentTarget.value;
            }

            if(this.searchData !== '') {
                let { data: size } = await this.axios.get(`/view/view_search_message_size/${this.searchData}`);
                if((this.limit - 1) * 10 > size) this.limit = 1;
                let searchInfo = [this.searchData, (this.limit - 1) * 10];
                let { data: message } = await this.axios.get(`/view/view_search_message/${searchInfo}`);
                this.messageInfo = message;

                let maxPage = Math.ceil(size / 10);
                let temp = [];
                this.currentPages = [];

                for(let i = 1; i <= maxPage; i++) {
                    temp.push(i);

                    if(i % 5 === 0) {
                        this.currentPages.push(temp);
                        temp = [];
                    } else if(i === maxPage) {
                        this.currentPages.push(temp);
                    }
                }
            } else {
                let { data: message } = await this.axios.get(`/view/view_message/${(this.limit - 1) * 10}`);
                this.messageInfo = message;

                let { data: size} = await this.axios.get(`/view/view_message_size`);
                let maxPage = Math.ceil(size / 10);
                let temp = [];
                this.currentPages = [];

                for(let i = 1; i <= maxPage; i++) {
                    temp.push(i);

                    if(i % 5 === 0) {
                        this.currentPages.push(temp);
                        temp = [];
                    } else if(i === maxPage) {
                        this.currentPages.push(temp);
                    }
                }
            }
        }
    }
}
</script>

<style scoped>
#wrap {
    width: 70%;
    margin: 3% auto;
    border: 2px solid #000000;
}

.message-list {
    width: 100%;
}

.title-area, .content-area {
    width: 100%;
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    text-align: center;
}

.title-area, .page {
    background-color: #000000;
    color: #ffffff;
}

.page {
    display: flex;
    justify-content: space-around;
}

.current, .next, .prev {
    cursor: pointer;
}

.select {
    color: #e770e7;
}

.search {
    width: 100%;
}

.search input {
    width: 100%;
    height: 100%;
    outline: 0;
    font-size: 1.1rem;
    text-align: center;
    border: none;
}
</style>