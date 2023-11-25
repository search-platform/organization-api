# organization-api
Service that manages organization entity and all related info like contacts and comments. Service can persist data and perform advanced searching


### GET organization-api -> chat-api
#### REQUEST:

#### RESPONSE:
```
{
	contacts: [
		{
			"type": PHONE
			"description": "Sales department",
			"value": "+96155867898"
		},
		{
			"type": EMAIL
			"description": "Sales department",
			"value": "raif-bank@gmail.com"
		}
	]
	"url": http://test-bank.html
	"logoLink": 
	"address": "Serbia, Belgrade, Kosavska 7B" //оставляет строкой т.к. информация может быть вообще разной и каких-то частей (город или улица или номер дома может не быть)
}
```
### GET /api/v1/search?type=ALL
#### REQUEST:
```
{
	"query": "raif"
}
```
#### RESPONSE:
type=ALL/ORGANIZATION в запросе - type ORGANIZATION в ответе
```
{
	"type": ORGANIZATION
 	"orgId": 123;
	"orgName": "Raiffizen Beograd",
	"orgLogoUrl": 
	"contactString": "Beograd, Kosav..." //обрезаем часть. Для просмотра хватит нескольких первых слов
}
```
type=PHONE/EMAIL в запросе - type CONTACT в ответе
```
{
	"type": CONTACT
	"orgId": 123;
 	"orgName": "Raiffizen Beograd",
  	"orgLogoUrl": 
 	"contactId": 123;
	"value": "raif-bank@gmail.com" / "+96155867898",
}
```

