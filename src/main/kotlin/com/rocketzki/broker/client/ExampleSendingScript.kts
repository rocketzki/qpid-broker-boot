/** hit ctrl+shift+F10 to run the script **/

package com.rocketzki.broker.client

import kotlin.system.exitProcess


val exampleMsg1 = "{}"
var exampleMsg2 = "message"
var exampleMsg3 = "{\"key\":\"someval\"}"



Client.send("someQueue", exampleMsg1)

Thread.sleep(500)

Client.send("someQueue", exampleMsg2)

Client.send("someQueue", exampleMsg3)

Thread.sleep(1000)




exitProcess(0)







