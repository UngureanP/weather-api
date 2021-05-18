<#import "adds/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main">
                <input type="text" name="cityName" value="${cityName!}"
                       placeholder="Search City">
                <input type="date" value="${date!}" name="date">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
            <div>
                <form method="get" action="/saveWeather">
                    <input type="text" name="itineraryKey" value="${itineraryKey!}"
                           placeholder="Key For Save">
                    <button type="submit" class="btn btn-success ml-2">Save</button>
                </form>
            </div>
        </div>
    </div>

    <div class="form-row">
        <form method="get" action="/getItinerary">
            <select name="id">
                <#if itineraries??>
                    <#list itineraries as itinerary>
                        <option name="id" value="${itinerary.id}">${itinerary.name}</option>
                    </#list>
                </#if>
            </select>
            <button type="submit" class="btn btn-primary ml-2">Select</button>
        </form>
    </div>

    <div>
        <table class=" table">
            <thead class="thead-dark">
            <tr class="tableHeader">
                <td scope="col">City</td>
                <td scope="col">Country</td>
                <td scope="col">Temperature</td>
                <td scope="col">Description</td>
                <td scope="col">Date and Time</td>
            </tr>

            <#if itinerarySet??>
                <#foreach itinerary in itinerarySet>
                    <tr class="tableBody">
                        <td scope="row">${itinerary.cityName}</td>
                        <td scope="row">${itinerary.country}</td>
                        <td scope="row">${itinerary.temperature}</td>
                        <td scope="row">${itinerary.description}</td>
                        <td scope="row">${itinerary.dateTime}</td>
                    </tr>
                </#foreach>
            </#if>
        </table>
    </div>
</@c.page>

