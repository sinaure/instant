import * as Amqp from "amqp-ts";

var connection = new Amqp.Connection("amqp://hub-iot:hub-iot@instant-host:5672");
var exchange = connection.declareExchange("main_exchange");
var queue = connection.declareQueue("main",{durable: false});

connection.on('open_connection', function() {
  console.log('opening.....')  
})
connection.on('trying_connect', function() {
  console.log('trying.....')  
})
connection.on('error_connection', function(err) {
  console.log(err)  
})
queue.bind(exchange);
queue.activateConsumer((message) => {
    var currentdate = new Date(); 
    var datetime = "Last Sync: " + currentdate.getDate() + "/"
                    + (currentdate.getMonth()+1)  + "/" 
                    + currentdate.getFullYear() + " @ "  
                    + currentdate.getHours() + ":"  
                    + currentdate.getMinutes() + ":" 
                    + currentdate.getSeconds();
    console.log("Message received at "+datetime+" : " + message.getContent());
});


