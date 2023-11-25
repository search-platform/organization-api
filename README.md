# organization-api
Service that manages organization entity and all related info like contacts and comments. Service can persist data and perform advanced searching

## API contracts
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

### GET /api/v1/organization/{organizationId}
#### RESPONSE:
```
{
	"orgName": "Raiffizen banka",
 	"address": "Beograd, Kosavska 7"
  	"orgUrl": "http",
   	"contacts" [
    		{
      			"type" PHONE,
	 		"value": "+78987987987"
		},
  		{
      			"type" EMAIL,
	 		"value": "raig@gmail.com"
		}
	]
}
```

### POST /api/v1/organization/{organizationId}/contact - создание существующего контакта
```
{
	"type": PHONE,
	"value": "new value"
}
```

### PUT /api/v1/organization/{organizationId}/contact/{contactId} - обновление существующего контакта
```
{
	"value": "new value"
}
```

## DB schema
https://dbdiagram.io/d/search-platform-65620c5d3be1495787b7f4de
