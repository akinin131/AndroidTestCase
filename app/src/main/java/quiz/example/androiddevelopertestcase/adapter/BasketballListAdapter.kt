package quiz.example.androiddevelopertestcase.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import quiz.example.androiddevelopertestcase.databinding.ItemMatchBinding
import quiz.example.androiddevelopertestcase.model.MatchItem

class BasketballListAdapter : RecyclerView.Adapter<BasketballListAdapter.MatchViewHolder>() {

    private val matches: MutableList<MatchItem> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setMatches(matches: List<MatchItem>) {
        this.matches.clear()
        this.matches.addAll(matches)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMatchBinding.inflate(inflater, parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches[position]
        holder.bind(match)
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    inner class MatchViewHolder(private val binding: ItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(match: MatchItem) {
            binding.eventDate.text = match.event_date
            binding.eventTime.text = match.event_time
            binding.eventHomeTeam.text = match.event_home_team
            binding.eventAwayTeam.text = match.event_away_team
            binding.eventFinalResult.text = match.event_final_result

        }
    }
}
