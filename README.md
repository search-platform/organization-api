# organization-api
Service that manages organization entity and all related info like contacts and comments. Service can persist data and perform advanced searching


GET organization-api -> chat-api
REQUEST:

RESPONSE:
{
	"phones": [
		{
			"description": "Sales department",
			"value": "+96155867898"
		}
	]
	"emails": [
		{
			"description": "Sales department",
			"value": "raif-bank@gmail.com"
		}
	]
	"url": http://test-bank.html
	"address": "Serbia, Belgrade, Kosavska 7B" //оставляет строкой т.к. информация может быть вообще разной и каких-то частей (город или улица или номер дома может не быть)
}
