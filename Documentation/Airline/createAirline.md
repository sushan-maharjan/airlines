Create an airline

    URL

        /rs/airline/new

    Method:
    
        POST

    URL Params

        None

    Data Params

        Required: name=[string]

    Success Response:
        Code: 200
        Content:
            {
                "id": 2,
                 "name": "New Airline"
            }

    Error Response:
        Code: 500 Server Error
