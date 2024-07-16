import { Component, OnDestroy, OnInit } from '@angular/core';
import { RxStompService } from '../shared/service/rx-stomp.service';
import { Message } from '@stomp/stompjs';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy  {
  messages: any[] = [];
  newMessage: string = '';
  userName: string= '';
// @ts-ignore, to suppress warning related to being undefine
  private topicSubscription: Subscription;

  constructor(private rxStompService: RxStompService) {}

  ngOnInit() {
    this.topicSubscription = this.rxStompService
      .watch('/topic/messages')
      .subscribe(res => {
        const data: Message = JSON.parse(res.body);
        this.messages.push(data);
      });
  }

  ngOnDestroy() {
    this.topicSubscription.unsubscribe();
  }

  onSendMessage() {
    this.rxStompService.publish({ destination: '/app/sendMessage', body: JSON.stringify({ content: this.newMessage, sender: this.userName } )});
  }
}
