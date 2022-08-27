var messageApi = Vue.resource('/user-inbox');

Vue.component('message-form',{
   data: function(){
       return {
           to:'',
           messageSubject:'',
           messageBody:''
       }
   },
   template: '<div>'+

       '<input type="text" placeholder="Send to" id="to" v-model="to"/><br>'+
       '<input type="text" placeholder="Subject" id="messageSubject" v-model="messageSubject"/><br>'+
       '<input type="text" placeholder="Message" id="messageBody" v-model="messageBody"/><br>'+
       '<input type="button" value="Save" @click="save"/>'+
       '</div>',
    methods: {
       save: function(){
           var message = { messageSubject:this.messageSubject, messageBody:this.messageBody,to:this.to }
           messageApi.save({},message).then(
               result => result.json().then(data => {
                   this.messages.push(data)
               })
           )
       }
    }
});

Vue.component('message-row',{
    props:['message','messages'],
    template: '<form><div>' +
        'Sender: <i>{{message.from.username}}</i><br>' +
        'Subject: <i>{{message.messageSubject}}</i><br>' +
        'Message:<br> {{message.messageBody}}'+
       // '<br><input type="text" name="_csrf" v-bind:value="_csrf.token" /><input type="button" value="Delete" @click="del"/>' +
        '</div></form>',
    methods:{
        del:function(){
            messageApi.remove({id:this.message.privateMessageId}).then(
                result => {if (result.ok){
                    this.messages.splice(this.messages.indexOf(this.message),1);
            }}
            )
        }
    }
});


Vue.component('messages-list',{
    props: ['messages'],
    template: '<div>' +
      '<message-form :messages="messages"/>'+
        '<message-row v-for="message in messages" :key="message.privateMessageId" :message="message" :messages="messages"/>' +
        '</div>'

});

var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages"/>',
    data: {
        messages: []
    },
    created: function(){
        messageApi.get().then(result => result.json().then(data =>
            data.forEach(message =>this.messages.push(message))
        ))
    }
});
